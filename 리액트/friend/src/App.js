import logo from './logo.svg';
import './App.css';
import data from './data.js';
import { useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';

function App() {

  let [friend, setFriend] = useState(data);
  
  let [inputs, setInputs] = useState({
    번호:'',
    이름:'',
    취미:'',
    생일:''
  });

  let onChange = (e) => {
    let {value, name} = e.target;
    setInputs({
      ...inputs,
      [name]:value
    });
    console.log(e.target.name);
  };

  return (
    <div className="App">
      <h1>내짝꿍</h1>
      <Link to="/push">친구추가하기</Link>/
      <Link to="/">메인으로가기</Link>
      <Routes>
        <Route path="/" element={
          <table align="center">
          <thead>
            <tr>
              <th>번호</th>
              <th width="90">이름</th>
              <th width="100">취미</th>
              <th width="120">생일</th>
              <th>기타</th>
            </tr>
          </thead>
          <tbody>
            {
              friend.map(function(f,i){
                return(
                  <Friend friend={friend} index={i} setFriend={setFriend}></Friend>
                )
              })
            }
          </tbody>
        </table>
        }></Route>
        <Route path="/push" element={
          <>
          <p>친구추가</p>
          번호 : <input name="번호" onChange={onChange}></input><br></br>
          이름 : <input name="이름" onChange={onChange}></input><br></br>
          취미 : <input name="취미" onChange={onChange}></input><br></br>
          생일 : <input name="생일" onChange={onChange} type="date"></input><br></br>
          <button onClick={()=>{
            let copy = [...friend];
            copy.push(inputs);
            setFriend(copy);
          }}>추가</button>
          </>
        }></Route>
      </Routes>
      
      
    </div>
  );
}

function Friend(props){
  return(
    <>
      <tr>
        <td>{props.index+1}</td>
        <td>{props.friend[props.index].이름}</td>
        <td>{props.friend[props.index].취미}</td>
        <td>{props.friend[props.index].생일}</td>
        <td><button onClick={()=>{
          let copy = [...props.friend];
          copy.splice(props.index,1);
          props.setFriend(copy);
        }}>삭제</button></td>
      </tr>
    </>
  )

}

export default App;
