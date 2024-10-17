SQL: Cryptocurrency Exchange Wallets Report

As part of the development of the "HackerCoin" cryptocurrency exchange platform, create a query that returns a list of Ethereum wallets and their balances 
based on transaction amounts.

Due to the dummy data nature of the development process, some wallets may end up with a negative balance. Be sure to exclude them from the report.

The result should have the following columns: address / transactions / balance.
﻿﻿		address - wallet address
﻿﻿		transactions - the number of confirmed transactions in a specific wallet; a confirmed transaction is one with confirmations ≥ 10
﻿﻿		balance - the sum of all the amount for all confirmed transactions in a specific wallet

The result should be sorted in descending order by balance.

Note:
﻿﻿Only transactions with ten or more confirmations should be included in the report.
﻿﻿Only wallets with a balance greater than zero should be included in the report.

Schema

			wallets
name		type			description
id			SMALLINT		unique ID, primary key
address		VARCHAR(255)

			transactions
name				type				description
wallet_id			SMALLINT		foreign key, wallets.id
amount				DECIMAL(6,3)
confirmations			SMALLINT	number of confirmations
• Sample Data Tables

			wallets
id				address

1				Oxce7a99fb564f654c1f48e9b687375b868553d7ec
2				0x7a70b14c1f883e5acca29d75413e803e4dab16d4
3				0x5d386db0077b732d273e2c00e5168d1da86b8239
4				0x3b14e880a15bbdbeaacc38c3a1dca182a209e953
5				Oxc45a4f90ddb00df4eb7df2cfe6a01 dcafd96fbcc

			transactions
wallet id	amount		confirmations
1			92.254		40
1			91.815		45
2			74.581		11
2			99.270		19
2			-34.418		38
2			-63.751		42
2			-44.135		46
3			7.081		7
3			-3.689		20
3			80.335		27
4			94.665		14
4			86.743		16
4			-22.050		43
5			﻿30.202		8
5			﻿52.135		13
5			﻿87.167		31
5			45.879		32
5			﻿70.605		37
5			﻿67.584		37
5			8.219		49

Expected output
address											transactions		balance
Oxce7a99fb564f654c1f48e9b687375b868553d7ec		2					184.069
0x3b14e880a15bbdbeaacc38c3a1dca182a209e953		3					159.358
0x5d386db0077b732d273e2c00e5168d1da86b8239		2					76.646
0x7a70b14c1f883e5acca29d75413e803e4dab16d4		5					31.547


SELECT 
    w.address, 
    COUNT(t.wallet_id) AS transactions, 
    SUM(t.amount) AS balance
FROM 
    wallets w
JOIN 
    transactions t 
ON 
    w.id = t.wallet_id
WHERE 
    t.confirmations >= 10
GROUP BY 
    w.address
HAVING 
    SUM(t.amount) > 0
ORDER BY 
    balance DESC;

Output
    address                                   | transactions | balance
    ------------------------------------------|--------------|---------
    0xce7a99fb564f654c1f48e9b687375b868553d7ec| 2            | 184.069
    0x3b14e880a15bbdbeaacc38c3a1dca182a209e953| 3            | 159.358
    0x5d386db0077b732d273e2c00e5168d1da86b8239| 2            | 76.646
    0x7a70b14c1f883e5acca29d75413e803e4dab16d4| 5            | 31.547

