
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

    const [createInvestigationopen, setCreateInvestigationOpen] = useState(false);
    const [updateInvestigationopen, setUpdateInvestigationOpen] = useState(false);
    const condition = true; 

    const [entity, setEntity] = useState("");

    const [masterBoard, setMasterBoard] = useState({});
    const [user, setUser] = useState({});
    const [boardDetail, setBoardDetail] = useState({});
    const [boardAttachFiles, setBoardAttachFiles] = useState();

    const retrieveDetail = () => {
        const retrieveDetailURL = `/assessments/${id}`;
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

    function fetchAssessment(id){
        axios.get(`/assessments/${id}`)
        .then(response => {
            setBoardDetail(response.data);
        })
    }

    function deleteList(){
        axios.delete(`/assessments/${id}`)
        navigate('/assessment/assessments');
    }

    function createInvestigation(){

        axios.put(`/assessments/${id}/createinvestigation`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setCreateInvestigationOpen(false);
                fetchAssessment(id);
            }
        });
    }
    function updateInvestigation(){

        axios.put(`/assessments/${id}/update`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setUpdateInvestigationOpen(false);
                fetchAssessment(id);
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
                        <li><Link to="/assessment/assessments">진위확인</Link></li>
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
                            <h1 className="tit_1">진위확인</h1>
                        </div>

                        {/* <!-- 게시판 상세보기 --> */}
                        <div className="board_view">
                            <div className="board_view_top">
                                <div className="tit">{id}</div>
                                <div className="info">
                                    <dl>
                                        <dt>진위확인</dt>
                                        <dd>{id}</dd>
                                    </dl>
                                    <dl>
                                        <dt>AccidentId</dt>
                                        <dd>{boardDetail && boardDetail.accidentId }</dd>
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
                                        <dt>AssessorId</dt>
                                        <dd>{boardDetail && boardDetail.assessorId }</dd>
                                    </dl>
                                    <dl>
                                        <dt>HospitalCode</dt>
                                        <dd>{boardDetail && boardDetail.hospitalCode }</dd>
                                    </dl>
                                    <dl>
                                        <dt>DoctorNote</dt>
                                        <dd>{boardDetail && boardDetail.doctorNote }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Results</dt>
                                        <dd>{boardDetail && boardDetail.results }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Date</dt>
                                        <dd>{boardDetail && boardDetail.date }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Comments</dt>
                                        <dd>{boardDetail && boardDetail.comments }</dd>
                                    </dl>
                                </div>
                            </div>
                            <div className="board_btn_area">
                                <div style={{ display: "flex", flexDirection: "row"}}>
                                    <div style={{marginTop: "5px"}}>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setCreateInvestigationOpen(true);
                                            }
                                        }}>
                                            사실조사 생성
                                        </button>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setUpdateInvestigationOpen(true);
                                            }
                                        }}>
                                            사실조사 판정
                                        </button>
                                    </div>
                                </div>
                                <div className="right_col btn1" style={{marginTop: "5px"}}>
                                    <Link to="/assessment/assessments"
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
                            <Dialog open={createInvestigationopen} onClose={() => setCreateInvestigationOpen(false)}>
                                <DialogTitle>사실조사 생성</DialogTitle>
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
                                    <button onClick={() => setCreateInvestigationOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={createInvestigation} className="btn btn_blue_h46 w_100">
                                    사실조사 생성
                                    </button>
                                </DialogActions>
                            </Dialog>
                        </div>
                        <div>
                            <Dialog open={updateInvestigationopen} onClose={() => setUpdateInvestigationOpen(false)}>
                                <DialogTitle>사실조사 판정</DialogTitle>
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
                                    <button onClick={() => setUpdateInvestigationOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={updateInvestigation} className="btn btn_blue_h46 w_100">
                                    사실조사 판정
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
