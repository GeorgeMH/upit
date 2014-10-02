SELECT 
    * 
FROM AuthSession 
WHERE userName = :input
OR email = :input
LIMIT 1
