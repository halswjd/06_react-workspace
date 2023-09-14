import { useState } from "react";

const DiaryEditor = ({onInput}) => {

    // 일기 저장 객체
    const [diary, setDiary] = useState({
        writer:"",
        content:"",
        emotion:1
    })

    // 넘길 요소 onChange 함수
    const onChangeValue = (e) => {
        setDiary({
            ...diary,
            [e.target.name]:e.target.value
        });
    }

    const diarySubmit = () => {
        onInput(diary.writer, diary.content, diary.emotion);
    }

    return(
        <div className="DiaryEditor">
            <h2>오늘의 일기</h2>
            <div>
                <input name="writer" onChange={onChangeValue}/>
            </div>
            <div>
                <textarea name="content" onChange={onChangeValue}/>
            </div>
            <div>
                오늘의 감정점수 : 
                <select name="emotion" onChange={onChangeValue}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <div>
                <button onClick={diarySubmit}>일기 저장하기</button>
            </div>
        </div>
    )
}

export default DiaryEditor;