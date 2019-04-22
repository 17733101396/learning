
let data = [];
let id = 1;
export default {

    'GET /post/selectPosts': (req, res) => {
        //res.status(401).send();
        res.json(data);
    },

    'GET /post/selectPostsByUser': (req, res) => {
        //res.status(401).send();
        console.log(req.query);
        res.json(data.filter(item => item.admin_id == req.query.admin_id));
    },


    'POST /post/deletePosts': (req, res) => {
        const {id} = req.body;
        console.log(req.body);
        data = data.filter(item => item.id !== id);
        console.log(data);
        res.json(1);
    },

    'POST /post/addPost': (req, res) => {
        console.log(req.body);
        req.body.id = id++;
        data.push(req.body);
        res.json(1);
    },

    'POST /post/updatePost': (req, res) => {
        console.log(req.body);
        const index = data.findIndex(item => item.id == req.body.id);
        console.log(index);
        data[index]['title'] = req.body.title;
        data[index]['content'] = req.body.content;
        data[index]['create_date'] = req.body.create_date;
        res.json(1);
    },

}