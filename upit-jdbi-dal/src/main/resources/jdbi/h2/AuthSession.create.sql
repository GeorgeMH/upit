INSERT INTO AuthSession (
    `sessionId`
    `userId`,
    `token`,
    `created`,
    `expires`,
    `active`
) VALUES (
    :sessionId,
    :userId,
    :token,
    :created,
    :expires,
    :active
)
