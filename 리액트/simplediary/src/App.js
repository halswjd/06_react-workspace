import { useRef, useState } from 'react';
import './App.css';
import DiaryEditor from './DiaryEditor';
import DiaryList from './DiaryList';

const dummyList = [


  // {
  //   id:1,
  //   author:"하민정",
  //   content:"하이1",
  //   emotion:5,
  //   created_date: new Date().getTime()
  // },
  // {
  //   id:2,
  //   author:"조준하",
  //   content:"하이2",
  //   emotion:3,
  //   created_date: new Date().getTime()
  // },
  // {
  //   id:3,
  //   author:"박용진",
  //   content:"하이3",
  //   emotion:4,
  //   created_date: new Date().getTime()
  // }
]

function App() {

  const [data, setData] = useState([]);
  const dataId = useRef(0);

  const onCreate = (author, content, emotion) => { // DiaryEditor에서 올 데이터를 받기
    const created_date = new Date().getTime();
    const newItem = {
      author,
      content,
      emotion,
      created_date,
      id : dataId.current
    }
    dataId.current += 1
    setData([newItem, ...data]) // 원본배열의 앞에 추가
  }

  const onDelete = (targetId) => {
    console.log(`${targetId}가 삭제됐습니다.`) // ` .. ` = +연산자 없이 변수와 문자를 같이 출력하는법 변수는 ${}로 구분
    const newDiaryList = data.filter((it)=> it.id !== targetId); // !== 타입까지도 같지 않을때
    setData(newDiaryList);
  }

  return (
    <div className="App">
      <DiaryEditor onCreate={onCreate}/>
      <DiaryList diaryList={data} onDelete={onDelete}/>
    </div>
  );
}

export default App;
