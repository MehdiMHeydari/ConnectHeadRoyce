const express = require('express')//go find the module express and bring it into this file
const bodyParser = require('body-parser')
const path = require('path')
const Database = require('better-sqlite3')

const db = new Database('./accounts.db')
db.exec('CREATE TABLE IF NOT EXISTS users (id integer PRIMARY KEY AUTOINCREMENT NOT NULL, username varchar(255), password varchar(255), salt varchar(255))')



const app = express(); //creates express application 
app.use(bodyParser.urlencoded({
    extended: true
}))


app.post('/register', function(req,res){
    console.log("received")
    let username = req.body.username;
    let password = req.body.password;
    let stmt = db.prepare('SELECT * from users WHERE username=?')
    let row = stmt.get(username)
    if(row == undefined){
        let insert = db.prepare('INSERT INTO users (username, password, salt) VALUES (?,?,?) ')
        insert.run(username, password, 'SALT')
        res.redirect('/login')
    }
    else{
        let filePath = path.join(__dirname, 'register.html')
        res.sendFile(filePath)
    }
})

app.get('/register',function(req,res){
    console.log("received")
    let filePath = path.join(__dirname, 'register.html') //base register page
    let filepath = path.join(__dirname, 're-register.html') //register page sent if user enters incorrect data
    if(req.query.username == null){ //resends register page if no username was entered
        res.sendFile(filePath)
    }
    function checkUser(){
        let checkuser = req.query.username;
        let stmt = db.prepare(`SELECT * from users WHERE username= '${checkuser}'`)
        if(stmt.run.length == 0){
            res.send("false")
        }
        else{
            res.send("true")
        }
    }

    
})

app.get('/login',function(req,res){
    console.log("received")
    let filePath = path.join(__dirname, 'login.html')
    res.sendFile(filePath)
})

app.post('/login', function(req,res){
    console.log("received")
    let stmt = db.prepare('SELECT * from users WHERE username=? AND password=?')
    let row = stmt.get(username, password)
    if(row == undefined){ 
        res.send('Login Failed')
    }  
    else{ //if success
        res.send('Login Succesful')
        let filePath = path.join(__dirname, 'login.html')
        res.sendFile(filePath)
    }

    
})


app.listen(8080, function(){
    console.log('server listening on port 8080')
})