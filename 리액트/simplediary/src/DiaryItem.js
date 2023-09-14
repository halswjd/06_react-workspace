import { useRef, useState } from "react";

const DiaryItem = ({author, content, created_date, emotion, id, onRemove, onEdit}) => { // 받은 diaryList 중에 ~~~를 쓰겠다

    // 수정하기 버튼 누르면 toggleIsEdit을 불러서 isEdit을 true로 바꾸기
    const [isEdit, setIsEdit] = useState(false); // isEdit 상태가 true면 수정하는거고 false면 안하는거고
    const toggleIsEdit = () => { setIsEdit(!isEdit) }

    const handleRemove = ()=>{
        //   console.log(id);
        if(window.confirm(`${id}번째 일기를 정말 삭제하시겠습니까?`)){
            onRemove(id);
        }
    }

    // 수정취소 눌렀을때 localContent값을 다시 원래대로
    const handleQuitEdit = () => {
        setIsEdit(false); // 수정안할거니까
        setLocalContent(content);
    }
    
    // 수정본을 담을 state
    const [localContent, setLocalContent] = useState(content);

    const localContentInput = useRef();
    const handleEdit = () => {
        if(localContent.length < 5){
            localContentInput.current.focus();
            return; // onEdit 못하게끔 막기
        }
        if(window.confirm(`${id}번째 일기를 수정하시겠습니까?`)){
            onEdit(id, localContent);
            toggleIsEdit();
        }
    }


    return(
        <div className="DiaryItem">
            <div className="info">
                <span>작성자 : {author} | 감정점수 : {emotion}</span>
                <br/>
                {/* data 타입 다루기 힘들어서 string으로 만들기 위해 ms으로 객체 만들고 date를 string으로 바꿔주는 메소드 이용 */}
                <span className="date">{new Date(created_date).toLocaleString()}</span>
            </div>
            <div className="content">
                {/* isEdit의 상태에 따라 다르게 보이게 */}
                {/* isEdit === true ? (수정원해) : (수정안원함) */}
                {
                    isEdit === true? (
                        <>
                            <textarea ref={localContentInput} value={localContent} onChange={(e)=>{setLocalContent(e.target.value)}}/>
                        </>
                    ) : (
                        <>
                            {content}
                        </>
                    )
                }
            </div>
            {
                // isEdit === true ? (수정원함) : (수정안원함)
                isEdit === true ? (
                    <>
                        <button onClick={handleQuitEdit}>수정취소</button>
                        <button onClick={handleEdit}>수정완료</button>
                    </>
                ) : (
                    <>
                        <button onClick={handleRemove}>삭제하기</button>
                        <button onClick={toggleIsEdit}>수정하기</button>
                    </>
                )
            }
        </div>
    )
}

export default DiaryItem;