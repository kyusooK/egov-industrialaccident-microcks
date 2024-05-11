
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

    const [applySalaryopen, setApplySalaryOpen] = useState(false);
    const [createSickLeaveBenefitopen, setCreateSickLeaveBenefitOpen] = useState(false);
    const [requestSickLeaveBenefitopen, setRequestSickLeaveBenefitOpen] = useState(false);
    const condition = true; 

    const [entity, setEntity] = useState("");

    const [masterBoard, setMasterBoard] = useState({});
    const [user, setUser] = useState({});
    const [boardDetail, setBoardDetail] = useState({});
    const [boardAttachFiles, setBoardAttachFiles] = useState();

    const retrieveDetail = () => {
        const retrieveDetailURL = `/sickLeaves/${id}`;
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

    function fetchSickLeave(id){
        axios.get(`/sickLeaves/${id}`)
        .then(response => {
            setBoardDetail(response.data);
        })
    }

    function deleteList(){
        axios.delete(`/sickLeaves/${id}`)
        navigate('/assessment/sickLeaves');
    }

    function applySalary(){

        axios.put(`/sickLeaves/${id}/applysalary`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setApplySalaryOpen(false);
                fetchSickLeave(id);
            }
        });
    }
    function createSickLeaveBenefit(){

        axios.put(`/sickLeaves/${id}/createsickleavebenefit`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setCreateSickLeaveBenefitOpen(false);
                fetchSickLeave(id);
            }
        });
    }
    function requestSickLeaveBenefit(){

        axios.put(`/sickLeaves/${id}/requestsickleavebenefit`, {id: entity }) 
        .then(response => {
            const resp = response.data
            if(!resp){
                navigate({pathname: URL.ERROR}, {state: {msg: resp.resultMessage}});
            }else{
                setRequestSickLeaveBenefitOpen(false);
                fetchSickLeave(id);
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
                        <li><Link to="/assessment/sickLeaves">휴업급여</Link></li>
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
                            <h1 className="tit_1">휴업급여</h1>
                        </div>

                        {/* <!-- 게시판 상세보기 --> */}
                        <div className="board_view">
                            <div className="board_view_top">
                                <div className="tit">{id}</div>
                                <div className="info">
                                    <dl>
                                        <dt>휴업급여</dt>
                                        <dd>{id}</dd>
                                    </dl>
                                    <dl>
                                        <dt>AccessmentId</dt>
                                        <dd>{boardDetail && boardDetail.accessmentId }</dd>
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
                                        <dt>AverageSalary</dt>
                                        <dd>{boardDetail && boardDetail.averageSalary }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Period</dt>
                                        <dd>{boardDetail && boardDetail.period }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Status</dt>
                                        <dd>{boardDetail && boardDetail.status }</dd>
                                    </dl>
                                    <dl>
                                        <dt>Date</dt>
                                        <dd>{boardDetail && boardDetail.date }</dd>
                                    </dl>
                                </div>
                            </div>
                            <div className="board_btn_area">
                                <div style={{ display: "flex", flexDirection: "row"}}>
                                    <div style={{marginTop: "5px"}}>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setApplySalaryOpen(true);
                                            }
                                        }}>
                                            평균임금 적용
                                        </button>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setCreateSickLeaveBenefitOpen(true);
                                            }
                                        }}>
                                            급여처리 생성
                                        </button>
                                        <button className="btn btn_blue_h46 w_100"
                                         onClick={() => {
                                            if (condition) {  
                                            setRequestSickLeaveBenefitOpen(true);
                                            }
                                        }}>
                                            휴업급여요청
                                        </button>
                                    </div>
                                </div>
                                <div className="right_col btn1" style={{marginTop: "5px"}}>
                                    <Link to="/assessment/sickLeaves"
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
                            <Dialog open={applySalaryopen} onClose={() => setApplySalaryOpen(false)}>
                                <DialogTitle>평균임금 적용</DialogTitle>
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
                                    <button onClick={() => setApplySalaryOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={applySalary} className="btn btn_blue_h46 w_100">
                                    평균임금 적용
                                    </button>
                                </DialogActions>
                            </Dialog>
                        </div>
                        <div>
                            <Dialog open={createSickLeaveBenefitopen} onClose={() => setCreateSickLeaveBenefitOpen(false)}>
                                <DialogTitle>급여처리 생성</DialogTitle>
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
                                    <button onClick={() => setCreateSickLeaveBenefitOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={createSickLeaveBenefit} className="btn btn_blue_h46 w_100">
                                    급여처리 생성
                                    </button>
                                </DialogActions>
                            </Dialog>
                        </div>
                        <div>
                            <Dialog open={requestSickLeaveBenefitopen} onClose={() => setRequestSickLeaveBenefitOpen(false)}>
                                <DialogTitle>휴업급여요청</DialogTitle>
                                <DialogContent>
                                    <TextField 
                                        autoFocus
                                        margin="dense"
                                        id="id"
                                        label="Id"
                                        type="text"
                                        fullWidth
                                        value={entity}
                                        onChange={(e) => setEntity(e.target.value)}
                                    />
                                </DialogContent>
                                <DialogActions>
                                    <button onClick={() => setRequestSickLeaveBenefitOpen(false)} className="btn btn_blue_h46 w_100">
                                        취소
                                    </button>
                                    <button onClick={requestSickLeaveBenefit} className="btn btn_blue_h46 w_100">
                                    휴업급여요청
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
