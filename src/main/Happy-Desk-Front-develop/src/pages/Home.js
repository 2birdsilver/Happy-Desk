import React, { useEffect, useState } from 'react';
import Desk from '../components/Desk.js';
import { useNavigate } from 'react-router-dom';
import SimpleSlider from '../components/SimpleSlider.js';


function Home() {
  // const [members, setMembers] = useState([]);
  const [team0, setTeam0] = useState([]);
  const [team1, setTeam1] = useState([]);
  const [team2, setTeam2] = useState([]);
  const [team3, setTeam3] = useState([]);
  const [team5, setTeam5] = useState([]);
  const [team6, setTeam6] = useState([]);
  const [team7, setTeam7] = useState([]);
  const navigate = useNavigate();

  const goToMemos = (member) => {
    navigate(`/memo/${member.id}`);
  };

  
  useEffect(() => {
    fetch('/members')
      .then((response) => response.json())
      .then((data) => {
        // setMembers(data);
        setTeam0(data.filter(member => member.dept === '0'));
        setTeam1(data.filter(member => member.dept === '1'));
        setTeam2(data.filter(member => member.dept === '2'));
        setTeam3(data.filter(member => member.dept === '3'));
        setTeam5(data.filter(member => member.dept === '5'));
        setTeam6(data.filter(member => member.dept === '6'));
        setTeam7(data.filter(member => member.dept === '7'));
      })
      .catch((error) => console.error("Fetching members failed", error));
  }, []);

  return (
    <div className="wrap">
      <SimpleSlider />
      <div className='container'>
        <div className='dept'>
          <h3 className='dept__title'>SI사업1팀</h3>
          <div className='dept__line'>
            {team1.map((member) => (
              <Desk key={member.id} member={member} onClick={() => goToMemos(member)}  />
            ))}
          </div>
        </div>
        <div className='dept'>
          <h3 className='dept__title'>SI사업2팀</h3>
          <div className='dept__line'>
            {team2.map((member) => (
              <Desk key={member.id} member={member} onClick={() => goToMemos(member)}  />
            ))}
          </div>
        </div>
        <div className='dept'>
          <h3 className='dept__title'>SI사업3팀</h3>
          <div className='dept__line'>
            {team3.map((member) => (
              <Desk key={member.id} member={member} onClick={() => goToMemos(member)}  />
            ))}
          </div>
        </div>
        <div className='dept'>
          <h3 className='dept__title'>SI사업5팀</h3>
          <div className='dept__line'>
            {team5.map((member) => (
              <Desk key={member.id} member={member} onClick={() => goToMemos(member)}  />
            ))}
          </div>
        </div>
        <div className='dept'>
          <h3 className='dept__title'>SI사업6팀</h3>
          <div className='dept__line'>
            {team6.map((member) => (
              <Desk key={member.id} member={member} onClick={() => goToMemos(member)}  />
            ))}
          </div>
        </div>
        <div className='dept'>
          <h3 className='dept__title'>금융사업팀</h3>
          <div className='dept__line'>
            {team7.map((member) => (
              <Desk key={member.id} member={member} onClick={() => goToMemos(member)}  />
            ))}
          </div>
        </div>
        <div className='dept'>
          <h3 className='dept__title'>임원진</h3>
          <div className='dept__line'>
            {team0.map((member) => (
              <Desk key={member.id} member={member} onClick={() => goToMemos(member)}  />
            ))}
          </div>
        </div>
        
     

       
      </div>
    </div>
  );
}

export default Home;
