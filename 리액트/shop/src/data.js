// let a = 10;
// let b = 100;

// export default 변수명;
// export default a;
// export {a,b}; 
// 변수 여러개 export 할 땐 default 없이
// 다른곳에서 변수를 쓸 수 있게끔 export한거임
// 불러올 땐 import 내보낼땐 export

// DB에서 가져온 정보가 있다 가정
let data = [
    {
        id : 0,
        title : "White and Black",
        content : "Born in France",
        price : 120000,
        img : "https://lovesykkkk.github.io/shoes1.jpg"
    },
    {
        id : 1,
        title : "Red Knit",
        content : "Born in Seoul",
        price : 110000,
        img : "https://lovesykkkk.github.io/shoes2.jpg"
    },
    {
        id : 2,
        title : "Gray Shoes",
        content : "Born in Incheon",
        price : 130000,
        img : "https://lovesykkkk.github.io/shoes3.jpg"
    }
]

export default data;