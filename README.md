### How to test

Execute the following request

```sh
curl --request POST \
  --url http://localhost:8080/landing-position \
  --header 'Content-Type: application/json' \
  --data '{
	"commands": "LMLMLMLMML",
	"probe": {
		"x": 1,
		"y": 2,
		"dir": "N"
	}
}'
```

This command set should produce this response:

```json
{
	"position": {
		"x": 1,
		"y": 3
	},
	"direction": "W"
}
```
