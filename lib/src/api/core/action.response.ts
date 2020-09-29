import Cell from "./cell";

interface ActionResponse {
    gameId: string;
    changedCells: Cell[];
    status: string;
}

export default ActionResponse;