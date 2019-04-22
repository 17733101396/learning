let data = [
    {
        id: 1,
        name: '经理',
        description: '我是经理'
    },
    {
        id: 2,
        name: '主管',
        description: '我是主管'
    },
    {
        id: 3,
        name: '员工',
        description: '我是员工'
    },
];

let id = 3;

export default {
    'GET /position/selectPositions': (req, res) => {
        //res.status(401).send();
        res.json(data);
    },

    'POST /position/deletePositions': (req, res) => {
        const {id} = req.body;
        console.log(req.body);
        data = data.filter(item => !id.includes(item.id));
        console.log(data);
        res.json(1);
    },

    'POST /position/addPosition': (req, res) => {
        console.log(req.body);
        req.body.id = ++id;
        data.push(req.body);
        res.json(1);
    },

    'POST /position/updatePosition': (req, res) => {
        console.log(req.body);
        const index = data.findIndex(item => item.id == req.body.id);
        console.log(index);
        data[index]['name'] = req.body.name;
        data[index]['description'] = req.body.description;
        res.json(1);
    },

}