UPDATE `User`
SET 
    userName = :userName, 
    email = :email, 
    password = :password, 
    dateCreated = :dateCreated,
WHERE id = :id
