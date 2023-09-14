const DiaryItem = () => {
    return(
        <div className="DiaryItem">
            <div className="info">
                <span>작성자 :  | 감정점수 : </span>
                <br/>
                {/* data 타입 다루기 힘들어서 string으로 만들기 위해 ms으로 객체 만들고 date를 string으로 바꿔주는 메소드 이용 */}
            </div>
            <div className="content">
           
            </div>
            
            <button>삭제하기</button>
            <button>수정하기</button>
                
           
        </div>
    )
}