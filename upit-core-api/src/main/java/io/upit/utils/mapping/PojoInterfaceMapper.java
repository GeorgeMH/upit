package io.upit.utils.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PojoInterfaceMapper {
    private final static Logger logger = LoggerFactory.getLogger(PojoInterfaceMapper.class);

    private static class ClassProperty<ParentInterface, ExistingInterface extends ParentInterface, TargetType extends ParentInterface> {
        public String propertyName;
        public ExistingInterface existingInstnace;
        public TargetType targetInstance;
        public Method getterMethod;
        public Method setterMethod;
    }

    public static <ParentInterface, ExistingInterface extends ParentInterface, TargetInterface extends ParentInterface>
     TargetInterface mapSiblingClass(Class<ParentInterface> parentInterfaceClass, Class<TargetInterface> targetInterfaceClass, ExistingInterface existingInstance) throws MappingException {
        if(null == existingInstance) {
            return null;
        } else if(targetInterfaceClass.isAssignableFrom(existingInstance.getClass())){
            return targetInterfaceClass.cast(existingInstance);
        }

        Map<String, ClassProperty> properties = processInterface(parentInterfaceClass);

        final TargetInterface newInstance;
        try {
            newInstance = targetInterfaceClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MappingException("Failed auto mapping Data object. Auto-mapped Data objects must have no-args constructors.", e);
        }

        for(Map.Entry<String, ClassProperty> entry : properties.entrySet()) {
            // TODO: We could apply soe sort of processing/introspection at the object mapping level
            String propertyName = entry.getKey();
            ClassProperty classProperty = entry.getValue();

            classProperty.propertyName = propertyName;
            classProperty.existingInstnace = existingInstance;

            Object propValue = null;
            try {
                propValue = classProperty.getterMethod.invoke(classProperty.existingInstnace);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new MappingException(("Failed retrieving property value: " + classProperty.existingInstnace.getClass().getName() + " | " + classProperty.getterMethod), e);
            }

            try {
                // TODO: Primitive support? We should generally just disallow primitives at the API layer eve though auto-boxing sucks.
                classProperty.setterMethod.invoke(newInstance, propValue);
            } catch (IllegalAccessException|InvocationTargetException e) {
                throw new MappingException("Failed setting property " + propertyName + " on new instnace " + newInstance.getClass().getName() + " | " + classProperty.setterMethod, e);
            }
        }

        return newInstance;
    }

    private static <ParentInterface> Map<String, ClassProperty> processInterface(Class<ParentInterface> parentInterfaceClass) {
        //TODO: This could be sped up using memoization, but at the cost of having to hold on to references of Class<?> objects.
        // this can cause class loader issues when you in a system that reloads classes at run-time.
        Map<String, ClassProperty> propertyMap = new HashMap<>();
        for(Method method : parentInterfaceClass.getMethods()) {

            boolean isRead = true;

            String methodName = method.getName();

            String propertyName = "";
            if(methodName.startsWith("is") || methodName.startsWith("get")){
                isRead = true;
                int propStartIdx = methodName.startsWith("is") ? "is".length() : "get".length();
                propertyName = methodName.substring(propStartIdx);
            } else if(methodName.startsWith("set")){
                isRead = false;
                propertyName = methodName.substring("set".length());
            } else {
                logger.info("Skipping unknown gett/setter prefix for method name: " + methodName + " on class: " + parentInterfaceClass.getName());
                continue;
            }

            ClassProperty classProperty = propertyMap.get(propertyName);
            if(null == classProperty) {
                classProperty = new ClassProperty();
                propertyMap.put(propertyName, classProperty);
            }

            if(isRead) {
                classProperty.getterMethod = method;
            } else {
                classProperty.setterMethod = method;
            }
        }
        return propertyMap;
    }
}
