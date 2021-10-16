require('dotenv').config()
const express = require('express')
const app = express()

const port = 3000
const db_pass = process.env.DB_PASSWORD

const { Pool, Client } = require('pg')

const pool = new Pool({
  user: 'nodejs',
  host: 'localhost',
  database: 'mytestdb',
  password: db_pass,
  port: 5432,
})


// an endpoint just for testing/demo
app.get('/time', async (req, res) => {
    console.log("GET /time")
    try {
        const time = await pool.query('SELECT NOW()')
        res.send(time.rows[0].now)
    } catch (err) {
        console.log(err.message)
    }
})

app.listen(port,  () => {
    console.log(`Listening on port ${port}`)
})