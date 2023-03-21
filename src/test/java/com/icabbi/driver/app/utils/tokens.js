// Utils to generate tokens in console
// Usage: 'npm run tokens:test'

const { TokenGenerator } = require('@icabbi/token-generator');
const tokenData = require('../config/auth-token-data.json');

async function getTokens(type, params) {
  try {
    let tokens = await TokenGenerator.getToken(type, params)

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

// getTokens("email", tokenData.driver.superAdmin);
// getTokens("email", tokenData.driver.driver);
getTokens("email", tokenData.fleet.superAdmin);
// getTokens("email", tokenData.booking.superAdmin);
