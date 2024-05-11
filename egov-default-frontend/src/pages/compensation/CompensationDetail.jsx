
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

    const [processCompensationopen, setProcessCompensationOpen] = useState(false);
    const [createCompensationopen, setCreateCompensationOpen] = useState(false);
    const condition = true; 

    const [entity, setEntity] = useState("");

    const [masterBoard, setMasterBoard] = useState({});
    const [user, setUser] = useState({});
    const [boardDetail, setBoardDetail] = useState({});
    const [boardAttachFiles, setBoardAttachFiles] = useState();

    const retrieveDetail = () => {
        const retrieveDetailURL = `/compensations/${id}`;
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

    function fetchCompensation(id){
        axios.get(`/compensations/${id}`)
        .then(response => {
            setBoardDetail(response.data);
        })
    }

    function deleteList(){
        axios.delete(`/compensations/${id}`)
        navigate('/compensation/compensations');
    }

    function processCompensation(){

        axios.put(`/compensations/${id}/processcompensation`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setProcessCompensationOpen(false);
                fetchCompensation(id);
            }
        });
    }
    function createCompensation(){

        axios.put(`/compensations/${id}/createcompensation`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setCreateCompensationOpen(false);
                fetchCompensation(id);
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
                        <li><Link to="/compensation/compensations">보상지급</Link></li>
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
                            <h1 className="tit_1">보상지급</h1>
                        </div>

                        {/* <!-- 게시판 상세보기 --> */}
                        <div className="board_view">
                            <div className="board_view_top">
                                <div className="tit">{id}</div>
                                <div className="info">
                                    <dl>
                                        <dt>보상지급</dt>
                                        <dd>{id}</dd>
                                    </dl>
                                    <dl>
                                        <dt>SickLeaveId</dt>
                                        <dd>{boardDetail && boardDetail.sickLeaveId }</dd>
                                    </dl>
                                    <dl>
                                        <dt>AssessmentId</dt>
                                        <dd>{boardDetail && boardDetail.assessmentId }</dd>
                                    </dl>
                                    <dl>
                                        <dt>AccidentId</dt>
                                        <dd>{boardDetail && boardDetail.accidentId }</dd>
                                    </dl>
                                    <dl>
                                        <dt>EmployeeId</dt>
                                        <dd>{boardDetail && boardDetail.employeeId }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Amount</dt>
                                        <dd>{boardDetail && boardDetail.amount }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Method</dt>
                                        <dd>{boardDetail && boardDetail.method }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Date</dt>
                                        <dd>{boardDetail && boardDetail.date }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Status</dt>
                                        <dd>{boardDetail && boardDetail.status }</dd>
                                    </dl>
                                </div>
                            </div>
                            <div className="board_btn_area">
                                <div style={{ display: "flex", flexDirection: "row"}}>
                                    <div style={{marginTop: "5px"}}>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setProcessCompensationOpen(true);
                                            }
                                        }}>
                                            지급처리
                                        </button>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setCreateCompensationOpen(true);
                                            }
                                        }}>
                                            보상처리 생성
                                        </button>
                                    </div>
                                </div>
                                <div className="right_col btn1" style={{marginTop: "5px"}}>
                                    <Link to="/compensation/compensations"
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
                            <Dialog open={processCompensationopen} onClose={() => setProcessCompensationOpen(false)}>
                                <DialogTitle>지급처리</DialogTitle>
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
                                    <button onClick={() => setProcessCompensationOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={processCompensation} className="btn btn_blue_h46 w_100">
                                    지급처리
                                    </button>
                                </DialogActions>
                            </Dialog>
                        </div>
                        <div>
                            <Dialog open={createCompensationopen} onClose={() => setCreateCompensationOpen(false)}>
                                <DialogTitle>보상처리 생성</DialogTitle>
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
                                    <button onClick={() => setCreateCompensationOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={createCompensation} className="btn btn_blue_h46 w_100">
                                    보상처리 생성
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
