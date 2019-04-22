let data = [
    {
        id: '10001',
        name: '赵峰',
        sex: 'male',
        birthday: '1996-10-30',
        phone: '123456789',
        email: '123456789@qq.com',
        address: '华南大学',
        dept_id: 1,
        dept_name: '研发部',
        pos_id: 1,
        pos_name: '经理'
    },
    {
        id: '10002',
        name: '孙悟空',
        sex: 'male',
        birthday: '2000-01-01',
        phone: '123456789',
        email: '123456789@qq.com',
        address: '神仙大学',
        dept_id: 2,
        dept_name: '人事部',
        pos_id: 2,
        pos_name: '主管'
    },
    {
        id: '10003',
        name: '李娜',
        sex: 'female',
        birthday: '1996-10-30',
        phone: '123456789',
        email: '123456789@qq.com',
        address: '华南大学',
        dept_id: 3,
        dept_name: '销售部',
        pos_id: 3,
        pos_name: '员工'
    },
];

let id = 10003;

export default {
    'GET /employee/selectEmployees': (req, res) => {
        //res.status(401).send();
        res.json(data);
    },

    'POST /employee/deleteEmployees': (req, res) => {
        const {id} = req.body;
        console.log(req.body);
        data = data.filter(item => !id.includes(item.id));
        console.log(data);
        res.json(1);
    },

    'POST /employee/addEmployee': (req, res) => {
        console.log(req.body);
        req.body.id = (parseInt(id) + 1).toString();
        data.push(req.body);
        res.json(1);
    },

    'POST /employee/updateEmployee': (req, res) => {
        console.log(req.body);
        const index = data.findIndex(item => item.id == req.body.id);
        console.log(index);
        data[index]['name'] = req.body.name;
        data[index]['sex'] = req.body.sex;
        data[index]['birthday'] = req.body.birthday;
        data[index]['phone'] = req.body.phone;
        data[index]['email'] = req.body.email;
        data[index]['address'] = req.body.address;
        data[index]['dept_id'] = req.body.dept_id;
        data[index]['dept_name'] = req.body.dept_name;
        data[index]['pos_id'] = req.body.pos_id;
        data[index]['pos_name'] = req.body.pos_name;
        res.json(1);
    },

}