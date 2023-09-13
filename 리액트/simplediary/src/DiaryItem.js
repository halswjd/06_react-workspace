const DiaryItem = ({author, content, created_date, emotion, id, onDelete}) => { // 받은 diaryList 중에 ~~~를 쓰겠다
    return(
        <div className="DiaryItem">
            <div className="info">
                <span>작성자 : {author} | 감정점수 : {emotion}</span>
                <br/>
                {/* data 타입 다루기 힘들어서 string으로 만들기 위해 ms으로 객체 만들고 date를 string으로 바꿔주는 메소드 이용 */}
                <span className="date">{new Date(created_date).toLocaleString()}</span>
            </div>
            <div className="content">{content}</div>
            <button onClick={()=>{
            //   console.log(id);
            if(window.confirm(`${id}번째 일기를 정말 삭제하시겠습니까?`)){
                onDelete(id);
            }
            }}>삭제하기</button>
        </div>
    )
}

export default DiaryItem;