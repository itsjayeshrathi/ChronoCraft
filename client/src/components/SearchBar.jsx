// src/components/SearchBar.js
import React, { useState } from 'react';
import '../styles/SearchBar.css'
function SearchBar({ onSearch }) {
    const [searchQuery, setSearchQuery] = useState('');

    const handleSearch = () => {
        onSearch(searchQuery);
    };

    return (
        <div className="search-bar mb-3">
            <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Search watches..."
                className="form-control"
            />
            <button className="btn btn-primary mt-2" onClick={handleSearch}>
                Search
            </button>
        </div>
    );
}

export default SearchBar;
