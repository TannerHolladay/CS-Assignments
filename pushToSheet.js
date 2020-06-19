// 1. npm install @bearer/node
// 2. Initializing the API client
const Bearer = require('@bearer/node')('FUFbE-aEBIbyWN5aVuX3wpWVp5pMOL8C')
const gsheet = Bearer.integration('google_sheets')

// 3. Define spreadsheet and values to append
const spreadsheetId = '1BvP_AeibLhU_cpk2JZ3sjDipEVSC9gH569m8p_cHz4o'
const data = [["firstname", "lastname"], ["John", "Doe"]]

// 4. Send data with a POST request
gsheet.auth('7fa704f0-6f15-11ea-b0ab-7d40a8525211')
.post(`${spreadsheetId}/values/A1:append`, {
  body: { values: data },
  query: { valueInputOption: 'RAW' }
}).then(() => { console.log('Saved!') })
