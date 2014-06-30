UPDATE AuthSession 
SET 
    userId = :userId, 
    token = :token, 
    created = :created, 
    expires = :expires, 
    lastAccessed = :lastAccessed, 
    active =:active
WHERE sessionId = :sessionId
