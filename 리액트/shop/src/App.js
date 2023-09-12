import './App.css';
import { Button } from 'react-bootstrap';
import {Navbar, Nav, Container, Row, Col} from 'react-bootstrap';
// import 작명 from '이미지경로';
// import bg from './img/bg.png';
import {a,b} from './data.js';
import data from './data.js'; // js파일은 생략해도 됨
import { useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';

function App() {


  let [shoes] = useState(data);
  // console.log(shoes[0].title); // 배열은 인덱스, 객체는 객체명으로 접근
  let [index, setIndex] = useState(0);

  return (
    <div className="App">
    <>

    <Navbar bg="dark" variant="dark">
      <Navbar.Brand href="#home">민정 마켓</Navbar.Brand>
      <Nav className="mr-auto">
        <Nav.Link href="/">Home</Nav.Link>
        <Nav.Link href="/detail">Cart</Nav.Link>
      </Nav>
    </Navbar>

    <Link to="/">홈</Link>
    <Link to="/detail">상세페이지</Link>

    <Routes>
      {/* <Route></Route> */}
      <Route path="/" element={
      <>
      
      <div className='main-bg'></div>
      {/* <div className='main-bg' style={{ backgroundImage:'url(' + bg + ')' }}></div> */}
      {/* 인라인요소로 이미지 스타일 지정시 이미지 import 필요 */}
      {/* 이미지가 src폴더에 있으면 반드시 import 필요, public에 있는 이미지는 import 없이 사용 가능 */}
      {/* <img src="/logo192.png" width="80%"/> */}
  
      <Container>
      <Row>
        {
          shoes.map(function(s, i){
            
            return(
              <Product shoes={shoes[i]} index={i+1}></Product>
            )
    
          })
  
        }
        
      </Row>
      </Container>
      </>
      }/>
      <Route path="/detail" element={
      <div>상세페이지임</div>
      }/> 
      {/* path가 /detail 일때 갈 페이지 element */}
      <Route path="/about" element={<div>어바웃페이지임</div>}/>
    </Routes>

    


    </>
    </div>
  );
}

function Product(props){
  let src = "https://lovesykkkk.github.io/shoes"+ (props.index+1) +".jpg"
  return(
    <Col sm>
      {/* <img src={props.shoes[props.index].img} width="80%"></img> */}
      {/* <img src={src} width="80%"></img> */}
      <img src={'https://lovesykkkk.github.io/shoes' + props.index + '.jpg'} width="80%"></img>
      <h4>{props.shoes.title}</h4>
      <p>{props.shoes.price}원</p>
    </Col>
  )
}

export default App;
