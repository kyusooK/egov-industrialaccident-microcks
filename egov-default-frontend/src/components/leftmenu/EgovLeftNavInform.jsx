import React from 'react';

import URL from 'constants/url';
import { NavLink } from 'react-router-dom';

function EgovLeftNavInform() { 
    const lastSegment = window.parent.location.href.split('/').pop();
    
    if (lastSegment === 'accidents'){
        return (
            <div className="nav">
                <div className="inner">
                    <h2>산재접수</h2>
                    <ul className="menu4">
                        <li><NavLink to="/accident/accidents" className={({ isActive }) => (isActive ? "cur" : "")}>산재접수</NavLink></li>
                    </ul>
                </div>
            </div>
        );
    }
    if (lastSegment === 'assessments'){
        return (
            <div className="nav">
                <div className="inner">
                    <h2>진위확인</h2>
                    <ul className="menu4">
                        <li><NavLink to="/assessment/assessments" className={({ isActive }) => (isActive ? "cur" : "")}>진위확인</NavLink></li>
                    </ul>
                </div>
            </div>
        );
    }
    if (lastSegment === 'sickLeaves'){
        return (
            <div className="nav">
                <div className="inner">
                    <h2>휴업급여</h2>
                    <ul className="menu4">
                        <li><NavLink to="/assessment/sickLeaves" className={({ isActive }) => (isActive ? "cur" : "")}>휴업급여</NavLink></li>
                    </ul>
                </div>
            </div>
        );
    }
    if (lastSegment === 'compensations'){
        return (
            <div className="nav">
                <div className="inner">
                    <h2>보상지급</h2>
                    <ul className="menu4">
                        <li><NavLink to="/compensation/compensations" className={({ isActive }) => (isActive ? "cur" : "")}>보상지급</NavLink></li>
                    </ul>
                </div>
            </div>
        );
    }
    if (lastSegment === 'statistics'){
        return (
            <div className="nav">
                <div className="inner">
                    <h2>Statistics</h2>
                    <ul className="menu4">
                        <li><NavLink to="/statistics/statistics" className={({ isActive }) => (isActive ? "cur" : "")}>Statistics</NavLink></li>
                    </ul>
                </div>
            </div>
        );
    }
    if (lastSegment === 'notifications'){
        return (
            <div className="nav">
                <div className="inner">
                    <h2>Notification</h2>
                    <ul className="menu4">
                        <li><NavLink to="/notification/notifications" className={({ isActive }) => (isActive ? "cur" : "")}>Notification</NavLink></li>
                    </ul>
                </div>
            </div>
        );
    }
    return null;
}

export default React.memo(EgovLeftNavInform);