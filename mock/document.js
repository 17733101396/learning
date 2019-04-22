import fs from 'fs';
import stream from 'stream';
let data = [
    {
        id: 1,
        name: '1.jpg',
        create_date: '2019-04-20',
        admin_id: 1
    }
];
let id = 1;
const dir = 'D:/桌面/doc-test/';
export default {

    'GET /document/selectDocuments': (req, res) => {
        //res.status(401).send();
        res.json(data);
    },

    'GET /document/selectDocumentsByUser': (req, res) => {
        //res.status(401).send();
        console.log(req.query);
        res.json(data.filter(item => item.admin_id == req.query.admin_id));
    },

    'POST /document/deleteDocuments': (req, res) => {
        const {id, name} = req.body;
        console.log(req.body);
        const url = dir + name;
        fs.unlink(url, function(err) {
            if (err) {
                console.log(err);
                res.json(0);
            }
        });
        data = data.filter(item => item.id !== id);
        console.log(data);
        res.json(1);
    },

    'POST /document/uploadDocument': (req, res) => {
        console.log(req.body, req.files[0]);
        const url = dir + req.files[0].originalname;
        if (fs.existsSync(url)) {
            console.log('文件已存在');res.status(400).send();
        } else {
            req.body.id = ++id;
            req.body.name = req.files[0].originalname;
            data.push(req.body);
            let ws = fs.createWriteStream(url);
            ws.write(req.files[0].buffer);
            ws.end();
            res.json(1);
        }
    },

    'GET /document/downloadDocument': (req, res) => {
        console.log(req.query);
        const {name} = req.query;
        const url = dir + name;
        if (!fs.existsSync(url)) {
            console.log('文件不存在');res.status('error').send();
        } else {
            res.setHeader("Content-Disposition", "attachment;filename=" + encodeURI(name)); //设置下载文件名
            fs.stat(url, function(err, stats) {
                res.setHeader("Content-Length", stats.size); //设置下载文件大小
            });
            let stream = fs.createReadStream(url);
            console.log(stream);
            let resData = []; //存储文件流
            if (stream) { //判断状态
                stream.on('data', function(chunk) {
                    resData.push(chunk);
                });
                stream.on('end', function() {
                    let finalData = Buffer.concat(resData);
                    console.log(finalData);
                    res.write(finalData);
                    res.end();
                });
            }

        }
    }



}