import { useRef, useState } from "react";

const DiaryEditor = ({onCreate}) => {

    // const [author, setAuthor] = useState("");
    // const [content, setContent] = useState("");

    const [state, setState] = useState({
        author:"",
        content:"",
        emotion:1
    })

    const handleChangeState = (e) => {
        setState({
            ...state,
            [e.target.name]:e.target.value
        })
    }

    const handleSubmit = () => {
        // console.log(state);
        if(state.author.length < 1){
            // console.log(authorInput.current);
            authorInput.current.focus();
            return;
        }
        if(state.content.length < 5){
            contentInput.current.focus();
            return;
        }

        // onCreate(state);
        onCreate(state.author, state.content, state.emotion);
        setState({
            author:"",
            content:"",
            emotion:1
        })
    }

    const authorInput = useRef();
    const contentInput = useRef();

    return(
        <div className="DiaryEditor">
        <h2>오늘의 일기</h2>
        <div>
            <input name="author" ref={authorInput} onChange={handleChangeState} value={state.author}/>
        </div>
        <div>
            <textarea name="content" ref={contentInput} onChange={handleChangeState}/>
        </div>
        <div>
            오늘의 감정점수 : 
            <select name="emotion" onChange={handleChangeState}>
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