/* eslint-disable */
import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  let post = '강남 우동 맛집';
  let [제목, 제목변경] = useState(['남자 코트 추천', '강남 우동 맛집', '파이썬 독학']);
  // let [따봉, 따봉변경] = useState(0);
  let [따봉, 따봉변경] = useState([0,0,0]);
  let [버튼, 버튼변경] = useState('남자코트추천');
  let [modal, setModal] = useState(false); // 보통 변경함수에는 set변수명으로 씀

  let [index, setIndex] = useState(0);
  let [입력값, 입력값변경] = useState('');

  [4,2,3].map(function(a){
    // console.log(1);
    // console.log(a);
    // return '123123'
  })

  // let[제목1, c] = useState('강남 우동 맛집');
  // 아래 제목을 바꾸는데에 state를 여러개 생성해서 써도 되지만
  // 위와같이 배열로 담아서 사용도 가능!

  // let num = [1,2];
  // let [a,c] = [1,2]; 이게 되는데 왜 state를 쓰지?

  // let[logo, setLogo] = useState('ReactBlog');
  
  function 함수(){
    console.log(1);
  }

  return (
    <div className="App">
      <div className="black-nav">
        <h4 style={{color:'red'}}>블로그임</h4>
      </div>
      {/* <h4>{ post }</h4> */}
      <div className='list'>
        {/* <h4>{제목[0]} <span onClick={ function(){console.log(1);} }>👍</span> {따봉} </h4> */}
        {/* <h4>{제목[0]} <span onClick={ () => { console.log(1)} }>👍</span> {따봉} </h4> */}
        {/* <h4>{제목[0]} <span onClick={ () => { 따봉 = 따봉 +1 } }>👍</span> {따봉} </h4> */}
        {/* 위처럼 등호로는 state 변경 안됨 ! => state 변경함수 필요 */}
        {/* <h4>{제목[0]} <span onClick={ () => { 따봉변경(따봉+1) } }>👍</span> {따봉} </h4> */}
        {/* span 클릭시 변수 변경이 자동으로 랜더링돼서 보이는걸 확인 가능, 기존에 배웠던 js로는 변수 변경만 되지 자동으로 랜더링이 이뤄지지는 않음 */}   
        <p>9월 11일 발행</p>
      </div>
      {/* <button onClick={ () => { 제목변경(['여자 코트 추천', '강남 우동 맛집', '파이썬 독학']) } }>변경</button> */}
      <button onClick={ () => {
        // 제목[0] = '여자코트 추천'; 
        // 제목변경(제목);
        // 제목변경(제목); => 변경되었다고 판단되면 바꿔주는 코드인데
        // 주소값을 통해 판단, 근데 주소값이 동일해서 바꿔지지 않는거임

        // let copy = 제목;
        // copy[0] = '여자코트추천';
        // 제목변경(copy);
        // 이거 또한 얕은복사개념이라 주소값을 copy에 복사하는거임

        let copy = [...제목];
        copy[0] = '여자코트추천';
        제목변경(copy);
        // 위와같이 [...복사할배열명] 깊은 복사를 통해 변경 가능

         } }>변경</button>

      <button onClick={ () => {
        
        let copy = [...제목.sort()];
        제목변경(copy);

        // 선생님 코드
        // let copy = [...제목];
        // copy.sort(); // sort() 메소드는 배열 자체를 바꿔주는 메소드
        // 제목변경(copy);

      } }>가나다순 정렬</button>

      <button onClick={()=>{
        let copy=제목;
        console.log(copy==제목); // true
        let copy1 = [...제목];
        console.log(copy1==제목); // false

        let copy2 = 제목;
        copy2[0] = '테스트';
        console.log(제목[0]); // '테스트' => 원본배열의 주소값을 복사(얕은복사)한거라 원본배열에 영향을 미침 

        }}>주소값</button>

      <div className='list'>
        <h4>{제목[1]}</h4>
        <p>9월 11일 발행</p>
      </div>
      <div className='list'>
        {/* <h4 제목 누르면 modal trune가 되게 하는 이벤트 핸들러>{제목[2]}</h4> */}
        <h4 onClick={()=>{ 
          // if(modal==false){
          //   setModal(true);
          // }else{
          //   setModal(false);
          // } if문 불가...? => 삼항연산자?

          // modal == false? setModal(true) : setModal(false);

          // 선생님 코드
          setModal(!modal);

        }}>{제목[2]}</h4>
        <p>9월 11일 발행</p>
      </div> 
    

      {
        // [1,2,3,4].map(function(){
        //   // return <div>안녕</div>
        //   return(
        //     <div className='list'>
        //       <h4>{제목[1]}</h4>
        //       <p>9월 11일 발행</p>
        //     </div>
        //   )
        // })

        제목.map(function(a, i){ // a : 값 자체,  i : 인덱스

          return(
            <div className='list'>
              <h4 onClick={()=>{ setModal(!modal); setIndex(i);}}>{제목[i]}<span onClick={ (e) => {
                e.stopPropagation(); // 상위 html로 퍼지는 이벤트 버블링을 막는 코드
                let copy = [...따봉]; // array 자료형을 바꿀때에는 카피본 만들고
                copy[i] = copy[i] + 1; // 값 수정하고
                따봉변경(copy); // 바꾼다, 공식처럼...

               } }>👍</span> {따봉[i]}</h4> 
              <p>9월 11일 발행</p>
              <button onClick={() => {
                let copy = [...제목];
                copy.splice(i, 1);
                제목변경(copy);

                // 따봉추가...
                let copy1 = [...따봉];
                copy1.unshift(0);
                따봉변경(copy1);
              }}>삭제</button>
            </div>
          )
          
        })
      }
      {/* 나는 따봉을 눌렀지만 상위 h4에 이벤트 핸들러가 있어서 모달이 열림 -> 막아주는 코드 필요 */}
      {/* 이벤트 버블링 */}


      {/* <input onInput={() => { console.log(1); }}></input> */}
      {/* <input onMouseOver={() => { console.log(1); }}></input> */}
      <input onChange={(e) => { 
        // console.log(e.target.value); 
        입력값변경(e.target.value);
        // console.log(입력값);
        // 한글자 입력시 console창에 입력된 값이 출력안됨 -> 입력값변경 함수를 실행하는 코드보다 console코드가 먼저 실행돼서
        }}></input>
        <button onClick={() => {
          let copy = [...제목];
          copy.unshift(입력값);
          
          제목변경(copy);

        }}>글 추가</button>
      {/* 이벤트 객체 정보를 e로 파라미터에 넘김 */}
      {/* e.target = 이벤트 객체 */}
      {/* 이벤트객체의 값을 담기 위해 state 선언하자! */}

      


      {/* 컴포넌트 불러오기 */}
      {/* <Modal></Modal> */}
      {/* <Modal/> */}
      {/* <Test/> */}

      {
        // 조건식 ? 참일때 실행할 코드 : 거짓일 때 실행할 코드 (삼항연산자)
        // 1 == 1? '맞음' : '거짓일 때 실행할 코드'
        // state 상태가 false면 <Modal> 안보이게
        // state 상태가 true면 <Modal> 보이게
        modal == true ? <Modal 제목={제목} 제목변경={제목변경} 인덱스={index} /> : null
      }

    </div>
  );
}

function Modal(props){
  return(
    <>
    <div className='modal'>
      {/* <h4>{제목[0]}</h4>  제목의 state가 다른 함수에서 선언됐기때문에 오류남 */}
      <h4>{props.제목[props.인덱스]}</h4>
      <p>날짜</p>
      <p>상세내용</p>
      <button onClick={() => {
        let copy = [...props.제목];
        copy[0] = '여자 코트 추천'
        props.제목변경(copy);
      }}>글수정</button>
    {/* </div><div></div> 이렇게 같은 태그를 병렬로하면 에러남 */}
    </div><div></div>
    </> /* 꼭 써야겠으면 의미없는 태그로 감싸면됨 */    


  )
}

function Test(){
  return(
    <div>
      <ul>
        <li>테스트1</li>
        <li>테스트2</li>
      </ul>
    </div>
  )
}

export default App;

/*
  * JSX 문법 특징
  1. class 속성 부여할 땐 className으로 부여할 것!ㅓ
  2. 변수 넣을 땐 {중괄호}, 변수는 요소의 아이디 클래스명으로 변수 사용 가능
  3. style 속성 부여할 땐 style={ { 스타일명 : '값' , 스타일명 : '값' , ... } } 로 표현할 것!
     '-'가 있는 스타일명은 낙타표기법으로 표현, ex) font-size -> fontSize
  4. 이벤트 핸들러 달때는 안에 함수명, 함수를 넣어야한다
  
  * state 만드는법
  1. import{useState}
  2. useState(보관할 자료)
  3. let [작명, 작명] : 작명은 마음대로
     > 첫번째 : state에 보관했던 자료
     > 두번째 : state를 변경해주는 함수
     let [a, b] = useState('남자 코트 추천');
     a는 useState('')안에 보관한 자료(남자 코트 추천), b는 state를 변경할 수 있는 함수

  * state 사용 이유
    > 일반변수 : 변경되면 html 자동 랜더링 안됨.
                ex) onclick 이벤트로 변수를 바꿔줘도 다시 그려주는(랜더링) 작업 필요
    > state : 변경되면 html 자동 랜더링 됨

  * state는 언제 쓰나
    > 변동시 자동으로 html에 반영되게 만들고 싶다면 state 사용할 것!
      변동할 일이 많은 요소가 아니라면 그냥 하드코딩으로 쓰는게 맞음
      자주 변경될 html이라면 state를 쓰자!

  * state 변경하는 법
    > state 변경함수(새로운 state의 값)
  
  * state 변경함수 특징
    > 기존 state == 신규 state의 경우 변경 안해줌
  
  * array / object 특징
    > array / object 담은 변수에는 주소값만 저장됨


  * 컴포넌트 만드는 법
  1. function 만들고 (함수명은 소문자로 시작하면 기본함수, 대문자로 시작해야 컴포넌트로 인식)
  2. return() 안에 html 담기
  3. <함수명></함수명> 쓰기

  * 컴포넌트로 만들면 좋은것
  1. 반복적인 html 축약할 때
  2. 큰 페이지들
  3. 자주 변경되는 것들

  * 컴포넌트 단점
  1. state 가져다 쓸 때 문제가 생김

  * 동적인 UI 만드는 방법
  1. HTML, CSS로 미리 디자인 완성
  2. UI의 현재상태를 state로 저장
  3. state에 따라 UI가 어떻게 보일지 작성

  * map 함수 특징
  1. array 자료 갯수만큼 함수안의 코드 실행해준다.
  2. 함수의 파라미터는 array안에 있던 자료임
  3. return문에 값을 적으면 array에 담아줌

  * map 함수 특징2 (state와 함께 썼을 때)
  1. 왼쪽 array 자료만큼 내부코드 실행해줌
  2. return (html 코드) => html코드를 array로 담아줌
  3. 유용한 파라미터 2개 사용 가능

  부모 -> 자식 state 전송하는법
  1. <자식컴포넌트 작명={state이름}>
  2. props 파라미터 등록
  3. props.작명 사용

*/