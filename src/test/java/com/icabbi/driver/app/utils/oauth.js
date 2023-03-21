const { TokenGenerator } = require('@icabbi/token-generator');

async function init(args) {
  const data = await _getArgAuthParams(args);
  try {
    let tokens = await TokenGenerator.getToken(data.type, data.params);
    if (tokens == {}) {
      tokens = {
        defaultToken: 'no-token',
      }
    }
    console.log(JSON.stringify(tokens));
  } catch (error) {
    console.log('Token Generation Failed With ' + error);
  }
}

async function _getArgAuthParams(args) {
  const stringParams = args[2];
  if (stringParams == undefined) {
    return {};
  }
  return JSON.parse(stringParams);
}

// This is the main call
const args = process.argv;
init(args);
