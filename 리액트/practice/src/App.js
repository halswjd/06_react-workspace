import { useRef, useState } from 'react';
import './App.css';
import DiaryEditor from './DiaryEditor';
import DiaryList from './DiaryList';

function App() {

  const [data, setData] = useState([]);
  const dataId = useRef(0);

  const onCreate = (author, content, emotion) => {
    const created_date = new Date().getTime();
    
    const newItem = {
      id:dataId.current,
      author,
      content,
      emotion,
      created_date
      // author:state.author,
      // content:state.content,
      // emotion:state.emotion,
      // created_date
    }

    setData([newItem, ...data]);
    dataId.current += 1;

  }

  const onDelete = (targetId) => {
    const newDiaryList = data.filter((it)=> it.id !== targetId);
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
