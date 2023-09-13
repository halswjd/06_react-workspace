import { useState, useRef } from "react";


const DiaryEditor = ({onCreate}) => {

    const authorInput = useRef();
    const contentInput = useRef();

    // const [author, setAuthor] = useState("");
    // const [content, setContent] = useState("");
    
    const [state, setState] = useState({
        author:"",
        content:"",
        emotion:1,
    });

    const handleChangeState = (e) => {
        // console.log(e.target.value);
        // console.log(e.target.name);

        setState({  
            ...state,
            [e.target.name]:e.target.value
        })
    }

    const handleSubmit = () => {
        // console.log(state);
        if(state.author.length < 1){
            // alert("작성자는 최소 1글자 이상 입력해주세요");

            // focus : 해당 input에 포커스되게
            authorInput.current.focus();
            return;
        }

        if(state.content.length < 5){
            // alert("일기 본문은 최소 5글자 이상 입력해주세요");

            // focus
            contentInput.current.focus();
            return;
        }
        onCreate(state.author, state.content, state.emotion);
        alert("저장성공!");
        setState({
            author:"",
            content:"",
            emotion:1,
        })
    } 



    return (    
        <div className="DiaryEditor">
            <h2>오늘의 일기</h2>
            <div>
                <input ref={authorInput}
                       name="author"
                       value={state.author} 
                    //    onChange={(e)=>{
                    //     setState({
                    //         // author:e.target.value,
                    //         // content:state.content
                    //         ...state,
                    //         author:e.target.value,
                    //     });
                    //    }}
                       onChange={handleChangeState}
                       />
            </div>
            <div>
                <textarea ref={contentInput}
                          name="content"
                          value={state.content}
                          //   onChange={(e)=>{
                              //     setState({
                                  //         // author:state.author,
                                  //         // content:e.target.value
                                  //         ...state,
                                  //         content:e.target.value
                                  //     });
                                  //   }}
                        onChange={handleChangeState}
                />
            </div>
            <div>
                오늘의 감정점수 : 
                <select name="emotion" value={state.emotion} onChange={handleChangeState}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <div>
                <button onClick={handleSubmit}>일기 저장하기</button>
            </div>
        </div>
    )
}

export default DiaryEditor;

//민정언니는 화이트 버전 어색어색!!! 검정 버전으로 하고 싶다.......
//하하하하....내일 회식 잘가
//요술포차가서 요술부리고 오세요ㅎㅎ
//술 적당히 마시고
//핸드폰 잘 챙기고
//언니 짐 잘 챙기고
//집 잘가고
//금요일에 지각하지 말고
//ㅋㅋㅋㅋㅋㅋ
//....