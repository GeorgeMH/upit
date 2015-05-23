package io.upit.dal.models;


public enum FileType {
    IMAGE(),
    VIDEO(),
    UNKNOWN();

    public static FileType getFileType(String mimeType) {
        if (null == mimeType) {
            return UNKNOWN;
        }

        if (mimeType.startsWith("image")) {
            return IMAGE;
        } else if (mimeType.startsWith("video")) {
            return VIDEO;
        }

        return UNKNOWN;
    }
}
