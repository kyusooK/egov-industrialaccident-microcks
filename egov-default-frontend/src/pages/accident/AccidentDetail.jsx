
import { useEffect, useState } from 'react'

import { Link, useLocation, useNavigate } from 'react-router-dom'

import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import TextField from '@mui/material/TextField';

import axios from 'axios';

import * as EgovNet from 'api/egovFetch'
import { NOTICE_BBS_ID } from 'config'
import CODE from 'constants/code'
import URL from 'constants/url'

import EgovAttachFile from 'components/EgovAttachFile'
import { default as EgovLeftNav } from 'components/leftmenu/EgovLeftNavInform'

function EgovNoticeDetail(props) {
    console.group("EgovNoticeDetail");
    console.log("EgovNoticeDetail [props] : ", props);

    const navigate = useNavigate();
    const location = useLocation();
    console.log("EgovNoticeDetail [location] : ", location);

    // const bbsId = location.state.bbsId || NOTICE_BBS_ID;
    const id = location.state.id;
    const searchCondition = location.state.searchCondition;

    const [applyMedicalBenefitopen, setApplyMedicalBenefitOpen] = useState(false);
    const [applySickLeaveBenefitopen, setApplySickLeaveBenefitOpen] = useState(false);
    const condition = true; 

    const [entity, setEntity] = useState("");

    const [masterBoard, setMasterBoard] = useState({});
    const [user, setUser] = useState({});
    const [boardDetail, setBoardDetail] = useState({});
    const [boardAttachFiles, setBoardAttachFiles] = useState();

    const retrieveDetail = () => {
        const retrieveDetailURL = `/accidents/${id}`;
        const requestOptions = {
            method: "GET",
            headers: {
                'Content-type': 'application/json'
            }
        }
        EgovNet.requestFetch(retrieveDetailURL,
            requestOptions,
            function (resp) {
                setBoardDetail(resp);
            }
        );
    }
    useEffect(function () {
        retrieveDetail();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    function fetchAccident(id){
        axios.get(`/accidents/${id}`)
        .then(response => {
            setBoardDetail(response.data);
        })
    }

    function deleteList(){
        axios.delete(`/accidents/${id}`)
        navigate('/accident/accidents');
    }

    function applyMedicalBenefit(){

        axios.put(`/accidents/${id}/applymedicalbenefit`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setApplyMedicalBenefitOpen(false);
                fetchAccident(id);
            }
        });
    }
    function applySickLeaveBenefit(){

        axios.put(`/accidents/${id}/applysickleavepay`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setApplySickLeaveBenefitOpen(false);
                fetchAccident(id);
            }
        });
    }


    return (
        <div className="container">
            <div className="c_wrap">
                {/* <!-- Location --> */}
                <div className="location">
                    <ul>
                        <li><Link to={URL.MAIN} className="home">Home</Link></li>
                        <li><Link to="/accident/accidents">산재접수</Link></li>
                        <li>{masterBoard && masterBoard.bbsNm}</li>
                    </ul>
                </div>
                {/* <!--// Location --> */}

                <div className="layout">
                    {/* <!-- Navigation --> */}
                    <EgovLeftNav></EgovLeftNav>
                    {/* <!--// Navigation --> */}

                    <div className="contents NOTICE_VIEW" id="contents">
                        {/* <!-- 본문 --> */}

                        <div className="top_tit">
                            <h1 className="tit_1">산재접수</h1>
                        </div>

                        {/* <!-- 게시판 상세보기 --> */}
                        <div className="board_view">
                            <div className="board_view_top">
                                <div className="tit">{id}</div>
                                <div className="info">
                                    <dl>
                                        <dt>산재접수</dt>
                                        <dd>{id}</dd>
                                    </dl>
                                    <dl>
                                        <dt>BusinessCode</dt>
                                        <dd>{boardDetail && boardDetail.businessCode }</dd>
                                    </dl>
                                    <dl>
                                        <dt>EmployeeId</dt>
                                        <dd>{boardDetail && boardDetail.employeeId }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Name</dt>
                                        <dd>{boardDetail && boardDetail.name }</dd>
                                    </dl>
                                    <dl>
                                        <dt>HospitalCode</dt>
                                        <dd>{boardDetail && boardDetail.hospitalCode }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Period</dt>
                                        <dd>{boardDetail && boardDetail.period }</dd>
                                    </dl>
                                    <dl>
                                        <dt>DoctorNote</dt>
                                        <dd>{boardDetail && boardDetail.doctorNote }</dd>
                                    </dl>
                                    <dl>
                                        <dt>AccidentType</dt>
                                        <dd>{boardDetail && boardDetail.accidentType }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Status</dt>
                                        <dd>{boardDetail && boardDetail.status }</dd>
                                    </dl>
                                    <dl>
                                        <dt>ApplyDt</dt>
                                        <dd>{boardDetail && boardDetail.applyDt }</dd>
                                    </dl>
                                </div>
                            </div>
                            <div className="board_btn_area">
                                <div style={{ display: "flex", flexDirection: "row"}}>
                                    <div style={{marginTop: "5px"}}>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setApplyMedicalBenefitOpen(true);
                                            }
                                        }}>
                                            요양급여 신청
                                        </button>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setApplySickLeaveBenefitOpen(true);
                                            }
                                        }}>
                                            휴업급여 신청
                                        </button>
                                    </div>
                                </div>
                                <div className="right_col btn1" style={{marginTop: "5px"}}>
                                    <Link to="/accident/accidents"
                                        className="btn btn_blue_h46 w_100">목록</Link>
                                </div>
                                <div className="right_col btn1" style={{marginTop: "5px", marginRight: "9%"}}>
                                    <button
                                        onClick={deleteList}
                                        className="btn btn_blue_h46 w_100">삭제
                                    </button>
                                </div>
                            </div>
                        </div>
                        {/* <!-- 게시판 상세보기 --> */}
                        <div>
                            <Dialog open={applyMedicalBenefitopen} onClose={() => setApplyMedicalBenefitOpen(false)}>
                                <DialogTitle>요양급여 신청</DialogTitle>
                                <DialogContent>
                                    <TextField 
                                        autoFocus
                                        margin="dense"
                                        id=""
                                        label=""
                                        type="text"
                                        fullWidth
                                        value={entity}
                                        onChange={(e) => setEntity(e.target.value)}
                                    />
                                </DialogContent>
                                <DialogActions>
                                    <button onClick={() => setApplyMedicalBenefitOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={applyMedicalBenefit} className="btn btn_blue_h46 w_100">
                                    요양급여 신청
                                    </button>
                                </DialogActions>
                            </Dialog>
                        </div>
                        <div>
                            <Dialog open={applySickLeaveBenefitopen} onClose={() => setApplySickLeaveBenefitOpen(false)}>
                                <DialogTitle>휴업급여 신청</DialogTitle>
                                <DialogContent>
                                    <TextField 
                                        autoFocus
                                        margin="dense"
                                        id=""
                                        label=""
                                        type="text"
                                        fullWidth
                                        value={entity}
                                        onChange={(e) => setEntity(e.target.value)}
                                    />
                                </DialogContent>
                                <DialogActions>
                                    <button onClick={() => setApplySickLeaveBenefitOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={applySickLeaveBenefit} className="btn btn_blue_h46 w_100">
                                    휴업급여 신청
                                    </button>
                                </DialogActions>
                            </Dialog>
                        </div>
                        
                        {/* <!--// 본문 --> */}
                    </div>
                </div>
            </div>
        </div>
    );
}
export default EgovNoticeDetail;
