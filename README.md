# SPACE PROBE

[![codecov](https://codecov.io/gh/rochajg/space-prbe/branch/maingraph/badge.svg?token=7oWri3d6pl)](https://codecov.io/gh/rochajg/space-prbe)

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


### Next Steps

- [ ] Add unit tests:
  - [ ] Add tests for existing classes;
  - [ ] Configure CI/CD tool;
  - [ ] Review coverage to be up to 80%;
- [ ] Create SpacialProbe manager:
  - [ ] Create new Probe;
  - [ ] Update Probe infos;
  - [ ] Get Probe infos;
