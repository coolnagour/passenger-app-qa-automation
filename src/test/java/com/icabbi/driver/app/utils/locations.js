// Util to generate location payload
// Usage: npm run locations

async function getLocations() {
  const locations = require('../config/locations.json')
  const now = new Date()
  const milliSecondsSinceEpoch = Math.round(now.getTime())
  locations.villeray.timestamp = milliSecondsSinceEpoch;
  const locationPayload = { locations: [ locations.villeray ]};
  const prettyLocations = JSON.stringify(locationPayload, null, 2);

  console.log(prettyLocations);

  return locations;
}

// This is the main call
getLocations();