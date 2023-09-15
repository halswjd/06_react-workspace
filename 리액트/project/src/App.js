import Navbars from './Navbars';
import Main from './Main';
import data from './data';
import { useState } from 'react';
import './App.css';


function App() {
  const [dataList,setDataList] = useState(data);
  return (
    <div className="App">
      <Navbars style={{paddingLeft:'20px'}}/>
      <Main dataList={dataList}/>
    </div>
  );
}

export default App;
