# SQL and JDBC notes

```sql
-- Finds customers with more than three orders.
SELECT customer_id, COUNT(*) AS order_count
FROM orders
GROUP BY customer_id
HAVING COUNT(*) > 3;

-- Finds duplicate email addresses.
SELECT email, COUNT(*) AS occurrences
FROM users
GROUP BY email
HAVING COUNT(*) > 1;
```

JDBC test approach:
1. Read connection details from a secret manager or environment variables.
2. Use try-with-resources for connections and statements.
3. Use PreparedStatement and bind parameters.
4. Assert returned rows and database state.
5. Roll back or clean up test data.

Prepare INNER vs LEFT JOIN, WHERE vs HAVING, keys, indexes, ACID, isolation levels and dirty/non-repeatable/phantom reads.
