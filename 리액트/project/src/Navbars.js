import { Nav, Navbar } from "react-bootstrap"

const Navbars = () => {
    return(
        <>
            <Navbar bg="light" data-bs-theme="light">
                <Navbar.Brand href="#home" style={{paddingLeft:'30px', fontWeight:'bold'}}>누리네</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="#home">Home</Nav.Link>
                    <Nav.Link href="#features">Features</Nav.Link>
                    <Nav.Link href="#pricing">Pricing</Nav.Link>
                </Nav>
            </Navbar>
            
        </>
    )
}

export default Navbars;