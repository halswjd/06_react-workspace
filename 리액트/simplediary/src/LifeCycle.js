import { useEffect, useState } from "react";

const Lifecycle = () => {

    const [count, setCount] = useState(0);
    const [text, setText] = useState("");

    // useEffect(콜백함수, [배열]);
    useEffect(()=>{
        console.log("Mount"); // 컴포넌트 탄생(Mount)
        // 왜 useEffect씀? 모든 그림이 그려진뒤에(랜더링된 후에) 실행되는거임
        // 먼저 사용자가 화면을 볼수있게하고 오래걸리는 작업들은 useEffect함수 이용하는거임
        
    }, []);

    // console.log("Mount");

    // 컴포넌트가 바뀔때마다 실행할 함수
    useEffect(()=>{// 배열 인자 빼면 수정(삭제 등) 실행할 함수
        console.log("Update!"); // 컴포넌트 변화(Update)
    });

    // ex)count 변화 일어날때만 실행되게할 함수
    // useEfffect(콜백함수,[state명])
    useEffect(()=>{
        console.log(`count is update : ${count}`)
        if(count > 5){
            alert("count가 5를 넘었어요 1로 초기화합니다.");
            setCount(1);
        }
    },[count]);

    // text 변화 일어날때만 실행될게할 함수
    useEffect(()=>{
        console.log(`text is update : ${text}`)
    },[text]);
    
    useEffect(()=>{
        console.log("UnMount!") // 컴포넌트 죽음(UnMount)

        return () => {
            // UnMount 시점에 실행됨
        }
    }, []);

    

    return(
        <div style={{padding:20}}>
            <div>
                {count}
                <button onClick={()=>{setCount(count+1)}}>+</button>
            </div>
            <div>
                <input value={text} onChange={(e)=>{setText(e.target.value)}}/>
            </div>
        </div>
    )
}

export default Lifecycle;