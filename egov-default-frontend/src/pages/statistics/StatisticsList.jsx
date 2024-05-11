
import { useCallback, useEffect, useRef, useState } from 'react'
import { Link, useLocation } from 'react-router-dom'

import * as EgovNet from 'api/egovFetch'
import { NOTICE_BBS_ID } from 'config'
import URL from 'constants/url'

import EgovPaging from 'components/EgovPaging'
import { default as EgovLeftNav } from 'components/leftmenu/EgovLeftNavInform'

import { itemIdxByPage } from 'utils/calc'

function EgovNoticeList(props) {

    const location = useLocation();
	const cndRef = useRef();
    const wrdRef = useRef();
    const bbsId = location.state?.bbsId || NOTICE_BBS_ID; 

	// eslint-disable-next-line no-unused-vars
    const [searchCondition, setSearchCondition] = useState(location.state?.searchCondition || { bbsId: bbsId, pageIndex: 1, searchCnd: '0', searchWrd: '' });// 기존 조회에서 접근 했을 시 || 신규로 접근 했을 시
    const [masterBoard, setMasterBoard] = useState({});
    const [user, setUser] = useState({});
    const [paginationInfo, setPaginationInfo] = useState({});

    const [listTag, setListTag] = useState([]);

    const retrieveList = useCallback((searchCondition) => {
        console.groupCollapsed("EgovNoticeList.retrieveList()");

        const retrieveListURL = '/statistics'+EgovNet.getQueryString(searchCondition);;
        const requestOptions = {
            method: "GET",
            headers: {
                'Content-type': 'application/json',
            }
        }

        EgovNet.requestFetch(retrieveListURL,
            requestOptions,
            (resp) => {

                let mutListTag = [];
                mutListTag.push(<p className="no_data" key="0">검색된 결과가 없습니다.</p>); // 게시판 목록 초기값
                
                const resultCnt = parseInt(resp.page.totalElements);
                const currentPageNo = resp.page.number;
                const pageSize = resp.page.size;

                // 리스트 항목 구성
                resp._embedded.statistics.forEach(function (item, index) {
                    if (index === 0) mutListTag = []; // 목록 초기화
                    const listIdx = itemIdxByPage(resultCnt , currentPageNo, pageSize, index);

                    mutListTag.push(
                        <Link
                            to={{pathname: "/statistics/StatisticsDetail"}}
                            state={{
                                id: item._links.self.href.split('/').pop(),
                                searchCondition: searchCondition
}}                            key={listIdx}
                            className="list_item">
                            <div>{item._links.self.href.split('/').pop()}</div>
                            <div>{item.accidentId}</div>    
                            <div>{item.hospitalCode}</div>    
                            <div>{item.businessCode}</div>    
                            <div>{item.results}</div>    
                            <div>{item.date}</div>    
                        </Link>
                    );
                });
                setListTag(mutListTag);
            },
            function (resp) {
                console.log("err response : ", resp);
            }
        );
        console.groupEnd("EgovNoticeList.retrieveList()");
    },[]);


    useEffect(() => {
        retrieveList(searchCondition);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    console.groupEnd("EgovNoticeList");
    return (
        <div className="container">
            <div className="c_wrap">
                {/* <!-- Location --> */}
                <div className="location">
                    <ul>
                        <li><Link to={URL.MAIN} className="home">Home</Link></li>
                        <li>산재신청 처리통계</li>
                        <li>Statistics</li>
                    </ul>
                </div>
                {/* <!--// Location --> */}

                <div className="layout">
                    {/* <!-- Navigation --> */}
                    <EgovLeftNav></EgovLeftNav>
                    {/* <!--// Navigation --> */}

                    <div className="contents NOTICE_LIST" id="contents">
                        {/* <!-- 본문 --> */}

                        <div className="top_tit">
                            <h1 className="tit_1">산재신청 처리통계</h1>
                        </div>

                        <h2 className="tit_2">Statistics</h2>

                        {/* <!-- 검색조건 --> */}
                        <div className="condition">
                            <ul>
                                <li className="third_1 L">
                                    <label className="f_select" htmlFor="sel1">
                                        <select id="sel1" title="조건" defaultValue={searchCondition.searchCnd} ref={cndRef}
                                            onChange={e => {
                                                cndRef.current.value = e.target.value; 
                                            }}
                                        >
                                            <option value="0">제목</option>
                                            <option value="1">내용</option>
                                            <option value="2">작성자</option>
                                        </select>
                                    </label>
                                </li>
                                <li className="third_2 R">
                                    <span className="f_search w_500">
                                        <input type="text" name="" defaultValue={searchCondition.searchWrd} placeholder="" ref={wrdRef}
                                            onChange={e => {
                                                wrdRef.current.value = e.target.value;
                                            }}
                                        />
                                        <button type="button"
                                            onClick={() => {
                                                retrieveList({ ...searchCondition, pageIndex: 1, searchCnd: cndRef.current.value, searchWrd: wrdRef.current.value });
                                            }}>조회</button>
                                    </span>
                                </li>
                                    <li>
                                        <Link to="/statistics/StatisticsEdit" state={{bbsId: bbsId}} className="btn btn_blue_h46 pd35">등록</Link>
                                    </li>
                            </ul>
                        </div>
                        {/* <!--// 검색조건 --> */}

                        {/* <!-- 게시판목록 --> */}
                        <div className="board_list BRD002">
                            <div className="head">

                                <span>Id</span>
                                <span>AccidentId</span>
                                <span>HospitalCode</span>
                                <span>BusinessCode</span>
                                <span>Results</span>
                                <span>Date</span>
                            
                            </div>
                            <div className="result">
                                {listTag}
                            </div>
                        </div>
                        {/* <!--// 게시판목록 --> */}

                        <div className="board_bot">
                            {/* <!-- Paging --> */}
                            <EgovPaging pagination={paginationInfo} moveToPage={passedPage => {
                                retrieveList({ ...searchCondition, pageIndex: passedPage, searchCnd: cndRef.current.value, searchWrd: wrdRef.current.value })
                            }} />
                            {/* <!--/ Paging --> */}
                        </div>

                        {/* <!--// 본문 --> */}
                    </div>
                </div>
            </div>
        </div>
    );
}


export default EgovNoticeList;


