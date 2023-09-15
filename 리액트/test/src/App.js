import { useRef, useState } from 'react';
import './App.css';
import { Link, Route, Routes } from 'react-router-dom';

function App() {

  const [data,setData] = useState([
    {
      id:"1",
      title:"Movie1",
      genre:"Drama",
      release_date:"2022-01-01"
    },
    {
      id:"2",
      title:"Movie2",
      genre:"Action",
      release_date:"2022-02-01"
    },
    {
      id:"3",
      title:"Movie3",
      genre:"Comedy",
      release_date:"2022-03-01"
    }
  ])

  // 새로운 영화 객체 받아서 추가하는 함수
  const addMovie = (id, title, genre, release_date) => {
    const newMovie={
      id,
      title,
      genre,
      release_date
    }
    setData([...data, newMovie]);
  }

  return (
    <div className="App">
      <div className='link-area'>
        <Link to="/" className='link'>List</Link>
        <Link to="/add">Add New Movie</Link>
      </div>
      <Routes>
        <Route path='/' element = {<List data={data} setData={setData}/>} ></Route>
        <Route path='add' element = {<AddMovie addMovie={addMovie} data={data}/>}/>
      </Routes>
    </div>
  );
}

function List({data, setData}){
  return(
    <div align="center">
      <h1>Movies</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th width="80">Title</th>
            <th width="80">Genre</th>
            <th width="120">Release Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {
            data.map((m)=>{
              return(
                <tr align="center" key={m.id}>
                  <td>{m.id}</td>
                  <td>{m.title}</td>
                  <td>{m.genre}</td>
                  <td>{m.release_date}</td>
                  <td><button onClick={()=>{
                    const newData = data.filter((d)=>(d.id !== m.id));
                    setData(newData);
                  }}>Delete</button></td>
                </tr>
              )
            })
          }
        </tbody>
      </table>
    </div>
  )
}

function AddMovie({addMovie, data}){

  const [newMovie,setNewMovie] = useState({
    id:"",
    title:"",
    genre:"",
    release_date:""
  })

  const handleOnChange = (e) => {
    setNewMovie({
      ...newMovie,
      [e.target.name]:e.target.value
    })
  }


  const idInput = useRef();
  const handleSubmit = () => {

    let result = 0;

    data.map((d)=>{
      if(d.id === newMovie.id){
        window.alert("중복된 id값입니다");
        idInput.current.focus();
        result = 1;
      }
      return result;
    })

    if(result===1){
      return;
    }

    alert("추가 성공");
    addMovie(newMovie.id, newMovie.title, newMovie.genre, newMovie.release_date);
    
    setNewMovie({
      id:"",
      title:"",
      genre:"",
      release_date:""
    })

    
    

  }


  return(
    <div align="center" className='AddMovie'>
      <h1>Create Movie</h1>
      <input ref={idInput} placeholder='input movie id' name="id" value={newMovie.id} onChange={handleOnChange} type="number" required/><br/>
      <input placeholder='input movie title' name="title" value={newMovie.title} onChange={handleOnChange} required/><br/>
      <input placeholder='input movie genre' name="genre" value={newMovie.genre} onChange={handleOnChange} required/><br/>
      출시일 : <input type='date' name="release_date" value={newMovie.release_date} onChange={handleOnChange} required/><br/>
      <button onClick={handleSubmit}>Add Movie</button>
    </div>
  )
}

export default App;
