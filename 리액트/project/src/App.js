import './App.css';
import Navbars from './Navbars';
import Main from './Main';
import data from './data';
import { useState } from 'react';


function App() {
  const [dataList,setDataList] = useState(data);
  return (
    <div className="App">
      <Navbars/>
      <Main dataList={dataList}/>
    </div>
  );
}

export default App;
