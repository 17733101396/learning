
let data = [
    {
        id: 1,
        username: 'superadmin',
        password: '123456',
        role_name: 'superadmin'
    },
    {
        id: 2,
        username: 'normaladmin',
        password: '123456',
        role_name: 'normaladmin'
    },
    {
        id: 3,
        username: 'admin',
        password: '123456',
        role_name: 'normaladmin'
    },
];

let id = 3;
export default {
    'GET /admin/selectAdmins': (req, res) => {
        //res.status(401).send();
        res.json(data);
    },

    'POST /admin/deleteAdmins': (req, res) => {
        const {id} = req.body;
        console.log(req.body);
        data = data.filter(item => !id.includes(item.id));
        console.log(data);
        res.json(1);
    },

    'POST /admin/addAdmin': (req, res) => {
        console.log(req.body);
        req.body.id = ++id;
        data.push(req.body);
        res.json(1);
    },

    'POST /admin/updateAdmin': (req, res) => {
        console.log(req.body);
        const index = data.findIndex(item => item.id == req.body.id);
        console.log(index);
        data[index]['username'] = req.body.username;
        data[index]['password'] = req.body.password;
        data[index]['role_name'] = req.body.role_name;
        res.json(1);
    },

    'POST /admin/login': (req, res) => {
        const {username, password} = req.body;
        if (password === '123456' && username === 'superadmin') {
            res.send({
                status: 'ok',
                id: 1,
                username: 'superadmin',
                role_name: 'superadmin',
                token: '123456',
            });
            console.log('ok');
            return;
        }
        if (password === '123456' && username === 'normaladmin') {
            res.send({
                status: 'ok',
                id: 2,
                username: 'normaladmin',
                role_name: 'normaladmin',
                token: '123456',
            });
            console.log('ok');
            return;
        }
        res.send({
            status: 'error',
            role_name: 'guest',
        });
    },
}