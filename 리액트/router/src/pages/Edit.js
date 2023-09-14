import { useNavigate, useSearchParams } from "react-router-dom";

const Edit = () => {
    
    const [searchParams, setSearchParams] = useSearchParams();

    const id = searchParams.get("id");
    console.log(id);

    const mode = searchParams.get("mode");
    console.log(mode);

    const navigate = useNavigate();
    
    return(
        <div>
            <h1>Edit</h1>
            <p>이곳은 일기수정 화면 입니다.</p>
            <button onClick={()=>{
                navigate("/");
            }}>Home으로 가기</button>
            <button onClick={()=>{
                navigate(-1);
            }}>뒤로가기</button>
        </div>
    )
}

export default Edit;