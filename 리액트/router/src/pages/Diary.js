import { useParams } from "react-router-dom";

const Diary = () => {

    const {id} = useParams();
    console.log(id);

    return(
        <div>
            <h1>Diary</h1>
            <p>이곳은 일기 상세화면 입니다.</p>
        </div>
    )
}

export default Diary;