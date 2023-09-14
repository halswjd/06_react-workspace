import { useRef, useState } from 'react';
import './App.css';
import DiaryEditor from './DiaryEditor';
import DiaryList from './DiaryList';

function App() {

  // 누적될 리스트 state
  const [dataList, setDataList] = useState([]);

  const dataId = useRef(0);

  // dataList에 추가할 함수
  const onInput = (writer, content, emotion) => {
    // console.log(writer + content + emotion);
    const created_date = new Date().getTime();

    const newDiary = {
      id:dataId.current,
      writer,
      content,
      emotion,
      created_date
    }

    // setDataList(newDiary);
    setDataList([...dataList, newDiary]);

    dataId.current += 1;

    console.log(dataList);
  }


  return (
    <div className="App">
      <DiaryEditor onInput={onInput}/>
      <DiaryList dataList={dataList}/>
    </div>
  );
}

export default App;
