CREATE TABLE `User` (
    `id` VARCHAR(32) NOT NULL,
    `userName` VARCHAR (32) NOT NULL,
    `email` VARCHAR (254) NULL,
    `password` VARCHAR(255) NOT NULL,
    `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY ( `id` ),
    UNIQUE (`userName`),
    INDEX User_userName_idx (`userName`),
    INDEX `Paste_email_idx` ( `email` )
) ENGINE=InnoDB;

CREATE TABLE `AuthSession` (
    `sessionId` VARCHAR(32) NOT NULL,
    `userId` VARCHAR(32) NOT NULL,
    `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `expires` DATETIME NOT NULL,
    `lastAccessed` DATETIME NOT NULL,
    `active` TINYINT(1) NOT NULL DEFAULT false,
    PRIMARY KEY (`sessionId`),
    INDEX AuthSession_userId_idx (`userId`),
    CONSTRAINT `AuthSession_userId_fk` FOREIGN KEY (`userId`) REFERENCES `User`(`id`)
) ENGINE=InnoDB;

CREATE TABLE `Paste` (
    `id` VARCHAR (32) NOT NULL,
    `text` LONGTEXT NOT NULL,
    `userId` VARCHAR(32) NOT NULL,
    `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `parentId` VARCHAR(32) NULL,
    `syntaxId` VARCHAR(32) NULL,
    PRIMARY KEY ( `id` ),
    INDEX `Paste_userId_idx` ( `userId` ),
    CONSTRAINT `Paste_userId_fk` FOREIGN KEY (`userId`) REFERENCES `User`(`id`)
)ENGINE=InnoDB;

