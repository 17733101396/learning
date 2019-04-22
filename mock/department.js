let data = [
    {
        id: 1,
        name: '研发部',
        description: '这是研发部'
    },
    {
        id: 2,
        name: '人事部',
        description: '这是人事部'
    },
    {
        id: 3,
        name: '销售部',
        description: '这是销售部'
    },
];

let id = 3;

export default {
    'GET /department/selectDepts': (req, res) => {
        //res.status(401).send();
        res.json(data);
    },

    'POST /department/deleteDepts': (req, res) => {
        const {id} = req.body;
        console.log(req.body);
        data = data.filter(item => !id.includes(item.id));
        console.log(data);
        res.json(1);
    },

    'POST /department/addDept': (req, res) => {
        console.log(req.body);
        req.body.id = ++id;
        data.push(req.body);
        res.json(1);
    },

    'POST /department/updateDept': (req, res) => {
        console.log(req.body);
        const index = data.findIndex(item => item.id == req.body.id);
        console.log(index);
        data[index]['name'] = req.body.name;
        data[index]['description'] = req.body.description;
        res.json(1);
    },

}