UPDATE Paste 
SET 
    `text` = :text, 
    userId = :userId,
    dateCreated = :dateCreated,
    parentId = :parentId,
    syntaxId = :syntaxId
WHERE sessionId = :sessionId
