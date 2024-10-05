import React from 'react'

interface SearchCriteria {
  id: string
  name: string
  grade: string
  java: string
  android: string
  javaee: string
}

interface SearchPanelProps {
  searchCriteria: SearchCriteria
  onSearchChange: (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => void
  onSearch: () => void // 添加 onSearch 回调
}

const SearchPanel: React.FC<SearchPanelProps> = ({ searchCriteria, onSearchChange, onSearch }) => {
  return (
    <div className='mb-4 grid grid-cols-1 gap-4'>
      <div className='flex flex-wrap gap-3'>
        <div className='flex items-center'>
          <label className='mr-1 text-sm font-semibold text-gray-700'>ID:</label>
          <input
            type='text'
            name='id'
            placeholder='请输入ID'
            value={searchCriteria.id}
            onChange={onSearchChange}
            className='input rounded-md border border-gray-300 p-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500'
          />
        </div>
        <div className='flex items-center'>
          <label className='mr-1 text-sm font-semibold text-gray-700'>姓名：</label>
          <input
            type='text'
            name='name'
            placeholder='请输入姓名'
            value={searchCriteria.name}
            onChange={onSearchChange}
            className='input rounded-md border border-gray-300 p-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500'
          />
        </div>
        <div className='flex items-center'>
          <label className='mr-1 text-sm font-semibold text-gray-700'>年级:</label>
          <select
            name='grade'
            value={searchCriteria.grade}
            onChange={onSearchChange}
            className='input appearance-none rounded-md border border-gray-300 bg-white p-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500'>
            <option value='' className='option-padding'>
              请选择年级
            </option>
            <option value='1'>1</option>
            <option value='2'>2</option>
            <option value='3'>3</option>
            <option value='4'>4</option>
          </select>
        </div>
      </div>
      <div className='flex flex-wrap gap-3'>
        <div className='flex items-center'>
          <label className='mr-1 text-sm font-semibold text-gray-700'>Java:</label>
          <input
            type='text'
            name='java'
            placeholder='Java成绩'
            value={searchCriteria.java}
            onChange={onSearchChange}
            className='input rounded-md border border-gray-300 p-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500'
          />
        </div>
        <div className='flex items-center'>
          <label className='mr-1 text-sm font-semibold text-gray-700'>Android:</label>
          <input
            type='text'
            name='android'
            placeholder='Android成绩'
            value={searchCriteria.android}
            onChange={onSearchChange}
            className='input rounded-md border border-gray-300 p-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500'
          />
        </div>
        <div className='flex items-center'>
          <label className='mr-1 text-sm font-semibold text-gray-700'>JavaEE:</label>
          <input
            type='text'
            name='javaee'
            placeholder='JavaEE成绩'
            value={searchCriteria.javaee}
            onChange={onSearchChange}
            className='input rounded-md border border-gray-300 p-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500'
          />
        </div>
      </div>
      <button
        onClick={onSearch}
        className='mt-4 w-full rounded-md bg-blue-500 p-2 text-white hover:bg-blue-600'>
        查询
      </button>
    </div>
  )
}

export default SearchPanel
