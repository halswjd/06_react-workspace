import DiaryItem from "./DiaryItem";

const DiaryList = ({ onRemove, diaryList, onEdit }) => { // props 등록하는 방법2
    return(
        <div className="DiaryList">
            <h2>일기 리스트</h2>
            <h4>{diaryList.length}개의 일기가 있습니다</h4>
            <div>
                {
                    // diaryList.map((it, idx)=>{
                    //     return()
                    // })
                    diaryList.map((it, idx)=>(
                        <DiaryItem key={it.id} {...it} onRemove={onRemove} onEdit={onEdit}/> // props 전달하는 방법2        
                        // <div key={it.id}>
                        //     <div>작성자 : {it.author}</div>
                        //     <div>일기 : {it.content}</div>
                        //     <div>감정 : {it.emotion}</div>
                        //     <div>작성시간(ms) : {it.created_date}</div>
                        // </div>
                    ))
                }
            </div>
        </div>

    )
}

DiaryList.defaultProps={ // 서버의 문제로 DiaryList의 데이터를 못가져올수 있는데 default 설정 안하면 에러뜸
                        // DiaryList의 props의 default를 설정해둬야함
    diaryList : [] // 서버 에러나도 0개의 일기가 있습니다.가 뜰 수 있게끔
}
export default DiaryList;


//모르겠누.....
//큰일났누.....
//어카누.....
//리액트 알려줄 민정언니 구함!!!!!!