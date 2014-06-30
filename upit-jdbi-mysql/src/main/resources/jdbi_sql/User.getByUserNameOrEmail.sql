SELECT 
    * 
FROM AuthSession 
WHER userName = :input
OR email = :input
LIMIT 1
