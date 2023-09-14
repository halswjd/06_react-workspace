import { Col, Container, Row } from "react-bootstrap";

const Main = ({dataList}) => {
    console.log(dataList);
    return(
        <>
            <div className='main-bg'></div>
            <Container fluid="md">
            <Row>
                <Col>
                <div className="main-titleImg" align="center">
                    
                    <img src={dataList[0].img} width="200" height="200"></img>
                    <div className="nuri-content">
                        이름 : 매 {dataList[0].name} <br/>
                        나이 : {dataList[0].age}<br/>
                        성별 : {dataList[0].gender}<br/>
                        별명 : {dataList[0].nickname}<br/>
                    </div>
                    {/* </div> */}
                </div>
                </Col>
            </Row>
            </Container>
        </>
    )
}

export default Main;